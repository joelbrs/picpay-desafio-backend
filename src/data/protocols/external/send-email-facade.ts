export interface SendEmailFacade {
    sendEmail: (email: string) => Promise<SendEmailFacade.Result>;
}

export namespace SendEmailFacade {
    export type Result = void;
}
