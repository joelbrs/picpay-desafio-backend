export const retrySyncCalls = async (
    url: string,
    n: number,
    options?: RequestInit
): Promise<any> => {
    try {
        const response = await fetch(url, options);

        if (!response.ok) {
            if (n <= 1) {
                throw new Error(
                    `HTTP Error! Status: ${response.status}, URL: ${url}`
                );
            }
            return await retrySyncCalls(url, n - 1, options);
        }
        return await response.json();
    } catch (err) {
        if (n <= 1) throw err;
        return await retrySyncCalls(url, n - 1, options);
    }
};
