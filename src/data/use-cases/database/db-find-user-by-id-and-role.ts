import { UserTypeEnum } from "@/domain/models";
import { FindUserByIdAndRole } from "@/domain/use-cases";
import { FindUserByIdAndRoleRepository } from "../../protocols/database/user/find-user-by-id-and-role.repository";

export class DbFindUserByIdAndRole implements FindUserByIdAndRole {
    constructor(
        private readonly findUserByIdAndRoleRepository: FindUserByIdAndRoleRepository
    ) {}

    async find(id: number, role: UserTypeEnum) {
        const user =
            await this.findUserByIdAndRoleRepository.findUserByIdAndRole(
                id,
                role
            );

        return user !== null;
    }
}
