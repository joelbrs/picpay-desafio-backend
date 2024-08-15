import { badRequest, ok } from "../helpers";
import { HttpResponse, Controller, Validation } from "../protocols";

export class CreateTransactionController implements Controller {
    constructor(private readonly validator: Validation) {}

    async handle(
        request: CreateTransactionController.Request
    ): Promise<HttpResponse> {
        const error = this.validator.validate(request);

        if (error) {
            return badRequest(error);
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
