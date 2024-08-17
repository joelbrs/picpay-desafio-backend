import { DbCheckBalance } from "@/data/use-cases";
import { CheckBalance } from "@/domain/use-cases";
import { PgUserRepository } from "@/infra/database/postgres/user-repository";

export const makeCheckBalance = (): CheckBalance => {
    const repository = new PgUserRepository();

    return new DbCheckBalance(repository);
};
