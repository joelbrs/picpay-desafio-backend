import { CheckBalanceRepository } from "@/data/protocols";
import { PgHelper } from "./helpers/connection";

export class PgUserRepository implements CheckBalanceRepository {
    async checkByUserId(
        id: number,
        value: number
    ): Promise<CheckBalanceRepository.Result> {
        const result = await PgHelper.client?.query(
            "--sql select sum(case when tt.payeeid = $1 then tt.amount when tt.payerid = $1 then -tt.amount else tt.amount end) > $2 as payer_balance from tb_transaction tt",
            [id, value]
        );

        return result?.rows[0] as boolean;
    }
}
