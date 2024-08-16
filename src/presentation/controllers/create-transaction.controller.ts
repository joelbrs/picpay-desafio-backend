import { CheckBalance, FindUserById } from "@/domain/use-cases";
import { badRequest, notFound, ok } from "../helpers";
import { HttpResponse, Controller, Validation } from "../protocols";
import {
    EntityNotFoundException,
    InsufficientBalanceException,
} from "../exceptions";

export class CreateTransactionController implements Controller {
    constructor(
        private readonly validator: Validation,
        private readonly checkBalance: CheckBalance,
        private readonly findUserById: FindUserById
    ) {}

    async handle(
        request: CreateTransactionController.Request
    ): Promise<HttpResponse> {
        const error = this.validator.validate(request);

        if (error) {
            return badRequest(error);
        }

        const { payer, value, payee } = request;

        const [payerEntity, payeeEntity] = await Promise.all([
            this.findUserById.findUserById(payer),
            this.findUserById.findUserById(payee),
        ]);

        if (!payerEntity || !payeeEntity) {
            return notFound(new EntityNotFoundException("User"));
        }

        const suffBalance = await this.checkBalance.checkByUserId(payer, value);
        if (!suffBalance) {
            return badRequest(new InsufficientBalanceException());
        }

        return ok({});
    }
}

export namespace CreateTransactionController {
    export type Request = {
        value: number;
        payer: number;
        payee: number;
    };
}
