export interface AuthorizeTransaction {
    autorize: () => Promise<AuthorizeTransaction.Result>;
}

export namespace AuthorizeTransaction {
    export type Result = boolean;
}
