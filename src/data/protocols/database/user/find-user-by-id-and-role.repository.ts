import { User, UserTypeEnum } from "@/domain/models";

export interface FindUserByIdAndRoleRepository {
    findUserByIdAndRole: (
        id: number,
        role: UserTypeEnum
    ) => Promise<FindUserByIdAndRoleRepository.Result>;
}

export namespace FindUserByIdAndRoleRepository {
    export type Result = User | null;
}
