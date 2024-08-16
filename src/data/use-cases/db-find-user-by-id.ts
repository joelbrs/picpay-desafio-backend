import { FindUserById } from "@/domain/use-cases";
import { FindUserByIdRepository } from "../protocols";

export class DbFindUserById implements FindUserById {
    constructor(
        private readonly findByUserIdRepository: FindUserByIdRepository
    ) {}

    async findUserById(id: number): Promise<FindUserById.Result> {
        return await this.findByUserIdRepository.findUserById(id);
    }
}
