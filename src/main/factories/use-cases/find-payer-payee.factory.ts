import { PgUserRepository } from "@/infra/database/postgres";
import { DbFindPayerPayeebyIds } from "@/data/use-cases";
import { FindPayerPayeeById } from "@/domain/use-cases";

export const makeFindPayerPayee = (): FindPayerPayeeById => {
    const repository = new PgUserRepository();

    return new DbFindPayerPayeebyIds(repository);
};
