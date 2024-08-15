import { HttpResponse, Controller, Validation } from "../protocols";

export class CreateTransactionController implements Controller {
    constructor(private readonly validator: Validation) {}

    async handle(
        request: CreateTransactionController.Request
    ): Promise<HttpResponse> {
        const error = this.validator.validate(request);

        if (error) {
            return { body: error, statusCode: 400 };
        }

        return { body: null, statusCode: 200 };
    }
}

export namespace CreateTransactionController {
    export type Request = {
        value: number;
        payer: number;
        payee: number;
    };
}
