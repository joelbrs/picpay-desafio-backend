import { UserTypeEnum } from "@/domain/models";
import { AccessDeniedException } from "../exceptions";
import { forbidden, ok, serverError } from "../helpers";
import { HttpResponse, Middleware } from "../protocols";

export class PayerMiddleware implements Middleware {
    constructor(
        private readonly findUserByIdAndRole: any,
        private readonly role: UserTypeEnum = UserTypeEnum.LOJISTA
    ) {}

    async handle(httpRequest: PayerMiddleware.Request): Promise<HttpResponse> {
        try {
            const { payer } = httpRequest;

            const user = await this.findUserByIdAndRole.find(payer, this.role);
            if (user) {
                return forbidden(new AccessDeniedException());
            }
            return ok({ payer });
        } catch (error) {
            return serverError(error as Error);
        }
    }
}

export namespace PayerMiddleware {
    export type Request = {
        payer: number;
    };
}
