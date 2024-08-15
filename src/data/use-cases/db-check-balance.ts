import { CheckBalance } from "@/domain/use-cases";
import { CheckBalanceRepository } from "../protocols/database/user";

export class DbCheckBalance implements CheckBalance {
    constructor(
        private readonly checkBalanceRepository: CheckBalanceRepository
    ) {}

    async checkByUserId(
        id: number,
        value: number
    ): Promise<CheckBalance.Result> {
        return await this.checkBalanceRepository.checkByUserId(id, value);
    }
}
