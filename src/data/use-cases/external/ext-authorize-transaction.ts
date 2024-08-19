import { AuthorizeTransactionFacade } from "@/data/protocols";
import { AuthorizeTransaction } from "@/domain/use-cases";

export class ExtAuthorizeTransaction implements AuthorizeTransaction {
    constructor(
        private readonly authorizeTransactionFacade: AuthorizeTransactionFacade
    ) {}

    async autorize(): Promise<boolean> {
        return this.authorizeTransactionFacade.authorize();
    }
}
