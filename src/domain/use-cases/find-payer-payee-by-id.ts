import { User } from "../models";

export interface FindPayerPayeeById {
    findPayerPayeeById: (
        payerId: number,
        payeeId: number
    ) => Promise<FindPayerPayeeById.Result>;
}

export namespace FindPayerPayeeById {
    export type Result = User | null;
}
