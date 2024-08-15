import { CreateTransactionController } from "@/presentation/controllers";
import { makeCreateTransactionValidation } from "./create-transaction-validation.factory";
import { makeCheckBalance } from "../use-cases";

export const makeCreateTransactionController = () => {
    const validator = makeCreateTransactionValidation();
    const checkBalanceRepo = makeCheckBalance();

    return new CreateTransactionController(validator, checkBalanceRepo);
};
