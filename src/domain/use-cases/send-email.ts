export interface SendEmail {
    send: (email: string) => Promise<SendEmail.Result>;
}

export namespace SendEmail {
    export type Result = void;
}
