export interface CheckBalance {
    checkByUserId: (id: number, value: number) => Promise<CheckBalance.Result>;
}

export namespace CheckBalance {
    export type Result = boolean;
}
