export interface CheckBalanceRepository {
    checkByUserId: (
        id: number,
        value: number
    ) => Promise<CheckBalanceRepository.Result>;
}

export namespace CheckBalanceRepository {
    export type Result = boolean;
}
