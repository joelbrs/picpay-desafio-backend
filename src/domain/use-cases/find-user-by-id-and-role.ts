import { UserTypeEnum } from "../models";

export interface FindUserByIdAndRole {
    find: (
        id: number,
        role: UserTypeEnum
    ) => Promise<FindUserByIdAndRole.Result>;
}

export namespace FindUserByIdAndRole {
    export type Result = boolean;
}
