import { CreateTransactionRepository } from "@/data/protocols";
import { Transaction } from "@/domain/models";
import { CreateTransaction } from "@/domain/use-cases";

export class DbCreateTransaction implements CreateTransaction {
    constructor(
        private readonly createTransactionRepository: CreateTransactionRepository
    ) {}

    async create(params: CreateTransaction.Params) {
        return this.createTransactionRepository.createTransaction(params);
    }
}
