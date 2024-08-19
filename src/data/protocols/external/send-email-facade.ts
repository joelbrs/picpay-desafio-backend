export interface SendEmailFacade {
    sendEmail: () => Promise<SendEmailFacade.Result>;
}

export namespace SendEmailFacade {
    export type Result = void;
}
