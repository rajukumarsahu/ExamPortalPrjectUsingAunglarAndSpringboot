/*
 * package com.exam.portal.jwt;
 * 
 * import java.io.IOException;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.context.SecurityContextHolder; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.web.authentication.
 * WebAuthenticationDetailsSource; import
 * org.springframework.stereotype.Component; import
 * org.springframework.web.filter.OncePerRequestFilter;
 * 
 * import com.exam.portal.imple.UserDetailsServiceImpl;
 * 
 * import io.jsonwebtoken.ExpiredJwtException; import
 * jakarta.servlet.FilterChain; import jakarta.servlet.ServletException; import
 * jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpServletResponse;
 * 
 * @Component public class JwtAuthenticationFilter extends OncePerRequestFilter
 * {
 * 
 * @Autowired private UserDetailsServiceImpl userDetailsServiceimpl;
 * 
 * @Autowired private JwtUtil jwtutil; // @Autowired // private
 * UserDetailsService userDetailsService;
 * 
 * @Override protected void doFilterInternal(HttpServletRequest request,
 * HttpServletResponse response, FilterChain filterChain) throws
 * ServletException, IOException {
 * System.out.println(request.getHeader("Authorization"));
 * 
 * final String reqTokenHeader=request.getHeader("Authorization");
 * System.out.println(reqTokenHeader); System.out.println("Token"); String
 * username=null; String jwtToken=null;
 * 
 * 
 * 
 * 
 * if(reqTokenHeader!=null && reqTokenHeader.startsWith("Bearer"))
 * 
 * {
 * 
 * jwtToken=reqTokenHeader.substring(7); try {
 * username=this.jwtutil.extractUsername(jwtToken); } catch (ExpiredJwtException
 * e) { e.printStackTrace(); System.out.println("jwtToken has Expired"); } catch
 * (Exception e) { e.printStackTrace(); System.out.println("error"); } } else {
 * System.out.println("Invalid TOken Not started with Bearer"); } //validate
 * if(username!=null &&
 * SecurityContextHolder.getContext().getAuthentication()==null) { final
 * UserDetails
 * userdetails=this.userDetailsServiceimpl.loadUserByUsername(username);
 * if(this.jwtutil.validateToken(jwtToken, userdetails)) {
 * UsernamePasswordAuthenticationToken usernamePasswordAuthenticationtoken=new
 * UsernamePasswordAuthenticationToken(userdetails,null,userdetails.
 * getAuthorities()); usernamePasswordAuthenticationtoken.setDetails(new
 * WebAuthenticationDetailsSource().buildDetails(request));
 * SecurityContextHolder.getContext().setAuthentication(
 * usernamePasswordAuthenticationtoken); }else {
 * System.out.println("token is not valid"); } filterChain.doFilter(request,
 * response); }
 * 
 * }
 * 
 * }
 */

package com.exam.portal.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.portal.imple.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceimpl;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{	
		final String authHeader = request.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		final String token = authHeader.substring(7);
		
		String username = null;
		try {
			username = this.jwtutil.extractUsername(token);		
		}
		catch (ExpiredJwtException e) {
			e.printStackTrace();
			System.out.println("JWT token has expired");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while extracting username from JWT token");
		}

		//validate
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			final UserDetails userDetails = this.userDetailsServiceimpl.loadUserByUsername(username);
			if(this.jwtutil.validateToken(token, userDetails)) {
				final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}else {
				System.out.println("JWT token is not valid");
			}
		}
		filterChain.doFilter(request, response);
	}
}
