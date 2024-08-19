import { Transaction } from "../models";

export interface CreateTransaction {
    create: (
        params: CreateTransaction.Params
    ) => Promise<CreateTransaction.Result>;
}

export namespace CreateTransaction {
    export type Params = {
        payee: number;
        payer: number;
        value: number;
    };

    export type Result = Transaction;
}
