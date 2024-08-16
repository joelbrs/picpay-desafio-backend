import { CreateTransactionController } from "@/presentation/controllers";
import { makeCreateTransactionValidation } from "./create-transaction-validation.factory";
import { makeCheckBalance } from "../use-cases";
import { makeFindPayerPayee } from "../use-cases/find-payer-payee";

export const makeCreateTransactionController = () => {
    const validator = makeCreateTransactionValidation();
    const checkBalanceRepo = makeCheckBalance();
    const findPayerPayeeId = makeFindPayerPayee();

    return new CreateTransactionController(
        validator,
        checkBalanceRepo,
        findPayerPayeeId
    );
};
