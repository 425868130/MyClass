

CREATE PROCEDURE [dbo].[Pce_User_Login_Check](@User_id NCHAR(12), @psd NCHAR(10))
AS
    SELECT *
    FROM [User]
    WHERE User_id = @User_id AND psd = @psd AND [User].[check] = 1
GO


