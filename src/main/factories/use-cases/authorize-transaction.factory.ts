import { ExtAuthorizeTransaction } from "@/data/use-cases";
import { AuthorizeTransaction } from "@/domain/use-cases";
import { TransactionFacadeImpl } from "@/infra/external";

export const makeAuthorizeTransaction = (): AuthorizeTransaction => {
    const facade = new TransactionFacadeImpl();

    return new ExtAuthorizeTransaction(facade);
};
