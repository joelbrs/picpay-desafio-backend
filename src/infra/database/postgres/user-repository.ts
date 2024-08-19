import {
    CheckBalanceRepository,
    FindPayerPayeeByIdsRepository,
} from "@/data/protocols";
import { FindUserByIdAndRoleRepository } from "@/data/protocols/database/user/find-user-by-id-and-role.repository";
import { UserTypeEnum } from "@/domain/models";
import { PgHelper } from "./helpers/connection";

export class PgUserRepository
    implements
        CheckBalanceRepository,
        FindPayerPayeeByIdsRepository,
        FindUserByIdAndRoleRepository
{
    async checkByUserId(id: number, val: number) {
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

        const result = await PgHelper.client?.query(query, [id, val]);
        return result?.rows[0].payer_balance as boolean;
    }

    async findPayerPayeeByIds(payerId: number, payeeId: number) {
        const query = `--sql
            WITH tb_joins AS (
                SELECT tu.id as id FROM tb_transaction tt 
                INNER JOIN tb_user tu ON tu.id = tt.payerid or tu.id = tt.payeeid 
            )
            
            SELECT EXISTS (
                SELECT id FROM tb_joins
                WHERE id = $1
            ) AND EXISTS (
                SELECT id FROM tb_joins
                WHERE id = $2
            ) AS users_exists;
        `;

        const result = await PgHelper.client?.query(query, [payerId, payeeId]);
        return result?.rows[0].users_exists;
    }

    async findUserByIdAndRole(id: number, role: UserTypeEnum) {
        const query = `--sql
            SELECT * FROM tb_user tu
            WHERE tu.id = $1 AND tu.userType = $2
        `;

        const result = await PgHelper.client?.query(query, [
            id,
            role.toString(),
        ]);
        return result?.rows[0] || null;
    }
}
