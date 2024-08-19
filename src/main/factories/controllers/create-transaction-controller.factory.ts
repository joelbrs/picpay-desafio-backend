import { CreateTransactionController } from "@/presentation/controllers";
import { makeCreateTransactionValidation } from "./create-transaction-validation.factory";
import {
    makeAuthorizeTransaction,
    makeCheckBalance,
    makeCreateTransactionUseCase,
    makeSendEmail,
} from "../use-cases";
import { makeFindPayerPayee } from "../use-cases/find-payer-payee.factory";

export const makeCreateTransactionController = () => {
    const validator = makeCreateTransactionValidation();
    const checkBalanceRepo = makeCheckBalance();
    const findPayerPayeeId = makeFindPayerPayee();
    const authorizeTransaction = makeAuthorizeTransaction();
    const createTransactionUseCase = makeCreateTransactionUseCase();
    const sendEmail = makeSendEmail();

    return new CreateTransactionController(
        validator,
        checkBalanceRepo,
        findPayerPayeeId,
        authorizeTransaction,
        sendEmail,
        createTransactionUseCase
    );
};
