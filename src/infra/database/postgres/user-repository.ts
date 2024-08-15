import { CheckBalanceRepository } from "@/data/protocols";
import { PgHelper } from "./helpers/connection";

export class PgUserRepository implements CheckBalanceRepository {
    async checkByUserId(
        id: number,
        value: number
    ): Promise<CheckBalanceRepository.Result> {
        const query = `--sql
        SELECT 
            SUM(
                CASE 
                    WHEN tt.payeeid = $1 THEN tt.amount 
                    WHEN tt.payerid = $1 THEN -tt.amount 
                    ELSE tt.amount 
                END
            ) > $2 AS payer_balance 
        FROM tb_transaction tt;`;

        const result = await PgHelper.client?.query(query, [id, value]);
        return result?.rows[0].payer_balance as boolean;
    }
}
