import { DbFindUserByIdAndRole } from "@/data/use-cases";
import { FindUserByIdAndRole } from "@/domain/use-cases";
import { PgUserRepository } from "@/infra/database/postgres";

export const makeFindUserByIdAndRole = (): FindUserByIdAndRole => {
    const repository = new PgUserRepository();

    return new DbFindUserByIdAndRole(repository);
};
