export interface FindPayerPayeeByIdsRepository {
    findPayerPayeeByIds: (
        payerId: number,
        payeeId: number
    ) => Promise<FindPayerPayeeByIdsRepository.Result>;
}

export namespace FindPayerPayeeByIdsRepository {
    export type Result = boolean;
}
