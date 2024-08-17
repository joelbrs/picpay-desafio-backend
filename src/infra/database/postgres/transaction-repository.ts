import { CreateTransactionRepository } from "@/data/protocols";
import { Transaction } from "@/domain/models";
import { CreateTransaction } from "@/domain/use-cases";
import { PgHelper } from "./helpers";

export class PgTransactionRepository implements CreateTransactionRepository {
    async createTransaction({ payee, payer, value }: CreateTransaction.Params) {
        const query = `--sql
            INSERT INTO tb_transaction (id, payerid, payeeid, amount) VALUES (DEFAULT, $1, $2, $3) RETURNING *;
        `;

        await PgHelper.client?.query("BEGIN;");
        await PgHelper.client?.query(
            "SET TRANSACTION ISOLATION LEVEL SERIALIZABLE"
        );
        const result = await PgHelper.client?.query(query, [
            payer,
            payee,
            value,
        ]);
        await PgHelper.client?.query("COMMIT;");
        return result?.rows[0] as Transaction;
    }
}
