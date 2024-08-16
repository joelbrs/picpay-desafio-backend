import { PgUserRepository } from "@/infra/database/postgres";
import { DbFindPayerPayeebyIds } from "@/data/use-cases";

export const makeFindPayerPayee = (): DbFindPayerPayeebyIds => {
    const repository = new PgUserRepository();

    return new DbFindPayerPayeebyIds(repository);
};
