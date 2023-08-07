package com.example.hybrid_kanbanboard.user.controller;

import com.example.hybrid_kanbanboard.status.MsgResponseDto;
import com.example.hybrid_kanbanboard.user.dto.SignUpRequestDto;
import com.example.hybrid_kanbanboard.user.service.EmailService;
import com.example.hybrid_kanbanboard.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hybrid")
public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    @PostMapping("/signup")
    public ResponseEntity<MsgResponseDto> signup(@RequestBody SignUpRequestDto requestDto){
        try {
            userService.signup(requestDto);
            return ResponseEntity.ok().body(new MsgResponseDto("회원가입 성공", HttpStatus.OK.value()));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new MsgResponseDto("회원가입 실패", HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping("/email")
    public String sendEmail(@RequestParam String email) throws Exception {
        String confirm = emailService.joinEmail(email);
        return confirm;
    }
}
