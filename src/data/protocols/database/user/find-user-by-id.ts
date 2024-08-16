import { User } from "@/domain/models";

export interface FindUserByIdRepository {
    findUserById: (id: number) => Promise<FindUserByIdRepository.Result>;
}

export namespace FindUserByIdRepository {
    export type Result = User | null;
}
