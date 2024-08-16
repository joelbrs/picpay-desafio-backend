import { User } from "../models";

export interface FindUserById {
    findUserById: (id: number) => Promise<FindUserById.Result>;
}

export namespace FindUserById {
    export type Result = User | null;
}
