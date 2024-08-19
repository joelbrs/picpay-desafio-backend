export interface AuthorizeTransactionFacade {
    authorize: () => Promise<AuthorizeTransactionFacade.Result>;
}

export namespace AuthorizeTransactionFacade {
    export type Result = boolean;
}
