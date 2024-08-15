import { CheckBalance } from "@/domain/use-cases";
import { badRequest, ok } from "../helpers";
import { HttpResponse, Controller, Validation } from "../protocols";
import { InsufficientBalanceException } from "../exceptions";

export class CreateTransactionController implements Controller {
    constructor(
        private readonly validator: Validation,
        private readonly checkBalance: CheckBalance
    ) {}

    async handle(
        request: CreateTransactionController.Request
    ): Promise<HttpResponse> {
        const error = this.validator.validate(request);

        if (error) {
            return badRequest(error);
        }

        const { payer, value } = request;

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
