import {
    AuthorizeTransaction,
    CheckBalance,
    FindPayerPayeeById,
    SendEmail,
} from "@/domain/use-cases";
import { badRequest, notFound, ok, serverError } from "../helpers";
import { HttpResponse, Controller, Validation } from "../protocols";
import {
    EntityNotFoundException,
    InsufficientBalanceException,
} from "../exceptions";

export class CreateTransactionController implements Controller {
    constructor(
        private readonly validator: Validation,
        private readonly checkBalance: CheckBalance,
        private readonly findPayerPayeeByIds: FindPayerPayeeById,
        private readonly authorizeTransaction: AuthorizeTransaction,
        private readonly sendEmail: SendEmail
    ) {}

    async handle(
        request: CreateTransactionController.Request
    ): Promise<HttpResponse> {
        try {
            const error = this.validator.validate(request);

            if (error) {
                return badRequest(error);
            }

            const { payer, value, payee } = request;

            const usersExists =
                await this.findPayerPayeeByIds.findPayerPayeeById(payer, payee);

            if (!usersExists) {
                return notFound(new EntityNotFoundException("User"));
            }

            const suffBalance = await this.checkBalance.checkByUserId(
                payer,
                value
            );
            if (!suffBalance) {
                return badRequest(new InsufficientBalanceException());
            }

            //Before Transaction is made
            await this.authorizeTransaction.autorize();

            //After Transaction is made
            await this.sendEmail.send();

            return ok({});
        } catch (error) {
            return serverError(error as Error);
        }
    }
}

export namespace CreateTransactionController {
    export type Request = {
        value: number;
        payer: number;
        payee: number;
    };
}
