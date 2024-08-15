import { DbCheckBalance } from "@/data/use-cases";
import { PgUserRepository } from "@/infra/database/postgres/user-repository";

export const makeCheckBalance = (): DbCheckBalance => {
    const repository = new PgUserRepository();

    return new DbCheckBalance(repository);
};
