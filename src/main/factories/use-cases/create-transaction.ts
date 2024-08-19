import { DbCreateTransaction } from "@/data/use-cases";
import { CreateTransaction } from "@/domain/use-cases";
import { PgTransactionRepository } from "@/infra/database/postgres";

export const makeCreateTransactionUseCase = (): CreateTransaction => {
    const repository = new PgTransactionRepository();

    return new DbCreateTransaction(repository);
};
