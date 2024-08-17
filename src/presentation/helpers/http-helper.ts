import { ServerErrorException } from "@/presentation/exceptions";
import { HttpResponse } from "@/presentation/protocols";

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

export const notFound = (error: Error): HttpResponse => ({
    statusCode: 404,
    body: error,
});

export const forbidden = (error: Error): HttpResponse => ({
    statusCode: 403,
    body: error,
});
