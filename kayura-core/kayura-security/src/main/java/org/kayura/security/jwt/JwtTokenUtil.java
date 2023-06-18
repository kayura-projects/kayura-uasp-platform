/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
 -----------------------------------------------------------------------------*/

package org.kayura.security.jwt;

import org.kayura.nextid.GuidGenerator;
import org.kayura.nextid.IdGenerator;
import org.kayura.security.core.LoginUserImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

public class JwtTokenUtil {

  private final Clock clock = JwtClock.INSTANCE;
  private final IdGenerator idGenerator = GuidGenerator.INSTANCE;

  private final String secretKey;
  private final Long expiration;

  public JwtTokenUtil(String secretKey, Long expiration) {
    this.secretKey = secretKey;
    this.expiration = expiration;
  }

  public String getIdFromToken(String token) {
    return getClaimsFromToken(token, Claims::getId);
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaimsFromToken(token, Claims::getExpiration);
  }

  public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  public Claims getAllClaimsFromToken(String token) {
    try {
      return Jwts.parserBuilder()
        .setSigningKey(getSecretKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
    } catch (JwtException ex) {
      throw new JwtParseException(ex.getMessage());
    }
  }

  private SecretKey getSecretKey() {
    byte[] encodeKey = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(encodeKey);
  }

  public Boolean isTokenExpired(Claims claims) {
    final Date expiration = claims.getExpiration();
    return expiration.before(clock.now());
  }

  public Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(clock.now());
  }

  public String createToken(LoginUserImpl loginUser) {

    final Date createdDate = clock.now();
    final Date expirationDate = calculateExpirationDate(createdDate);

    String jwtId = idGenerator.nextId();
    String token = Jwts.builder()
      .setId(jwtId)
      .setSubject(loginUser.getUserId())
      .setIssuer(loginUser.getAppId())
      .setIssuedAt(createdDate)
      .setExpiration(expirationDate)
      .signWith(getSecretKey())
      .compact();

    return token;
  }

  private Date calculateExpirationDate(Date createdDate) {
    return new Date(createdDate.getTime() + expiration * 1000L);
  }

  public JwtToken refreshToken(String token) {

    final Date createdDate = clock.now();
    final Date expirationDate = calculateExpirationDate(createdDate);
    final Claims claims = getAllClaimsFromToken(token);

    String newToken = Jwts.builder()
      .setId(claims.getId())
      .setSubject(claims.getSubject())
      .setExpiration(expirationDate)
      .signWith(getSecretKey())
      .compact();

    return new JwtToken(claims.getId(), newToken);
  }

}
