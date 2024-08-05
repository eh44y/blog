import ResponseDto from "../response.dto";

export default interface SignInResponseDto extends ResponseDto {
  token: {
    grantType: string;
    accessToken: string;
    tokenExpiresIn: number;
  };
  expirationTime: number;
} // SignInResponseDto
