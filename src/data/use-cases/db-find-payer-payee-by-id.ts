import { FindPayerPayeeById } from "@/domain/use-cases";
import { FindPayerPayeeByIdsRepository } from "../protocols";

export class DbFindPayerPayeebyIds implements FindPayerPayeeById {
    constructor(
        private readonly findPayerPayeeByIdRepository: FindPayerPayeeByIdsRepository
    ) {}

    async findPayerPayeeById(payerId: number, payeeId: number) {
        return await this.findPayerPayeeByIdRepository.findPayerPayeeByIds(
            payerId,
            payeeId
        );
    }
}
