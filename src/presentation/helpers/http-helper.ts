import { HttpResponse } from "@/presentation/protocols";
import { ServerErrorException } from "@/presentation/exceptions";

export const badRequest = (error: Error): HttpResponse => ({
    statusCode: 400,
    body: error,
});

export const serverError = (error: Error): HttpResponse => ({
    statusCode: 500,
    body: new ServerErrorException(error.stack),
});

export const ok = (data: any): HttpResponse => ({
    statusCode: 200,
    body: data,
});

export const noContent = (): HttpResponse => ({
    statusCode: 204,
    body: null,
});
