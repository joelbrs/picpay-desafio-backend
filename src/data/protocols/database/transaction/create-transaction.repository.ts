import { Transaction } from "@/domain/models";
import { CreateTransaction } from "@/domain/use-cases";

export interface CreateTransactionRepository {
    createTransaction: (
        params: CreateTransactionRepository.Params
    ) => Promise<CreateTransactionRepository.Result>;
}

export namespace CreateTransactionRepository {
    export type Params = CreateTransaction.Params;

    export type Result = Transaction;
}
