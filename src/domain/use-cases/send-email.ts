export interface SendEmail {
    send: () => Promise<SendEmail.Result>;
}

export namespace SendEmail {
    export type Result = void;
}
