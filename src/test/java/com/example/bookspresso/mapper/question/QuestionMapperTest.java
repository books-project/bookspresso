package com.example.bookspresso.mapper.question;

import com.example.bookspresso.dto.member.MemberJoinDTO;
import com.example.bookspresso.dto.question.board.*;
import com.example.bookspresso.dto.question.page.QPageRequestDTO;
import com.example.bookspresso.mapper.member.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class QuestionMapperTest {
@Autowired QuestionMapper questionMapper;
@Autowired
MemberMapper memberMapper;
    QuestionWriteDTO questionWriteDTO;
    MemberJoinDTO memberJoinDTO;

    @BeforeEach
    void setUp() {
        memberJoinDTO = MemberJoinDTO.builder()
                .memberId(1L)
                .loginId("aaaa")
                .password("aaaaaa")
                .email("sfdmfse@naver.com")
                .nickname("aaa@aa")
                .build();

        memberMapper.insertMember(memberJoinDTO);

        questionWriteDTO = QuestionWriteDTO.builder()
                .qTitle("aaaa")
                .qContent("aaaaa")
                .memberId(1L)
                .build();

        QuestionWriteDTO questionWriteDTO = new QuestionWriteDTO();
        questionWriteDTO.setMemberId(3L);
        questionWriteDTO.setQContent("test@");
        questionWriteDTO.setQTitle("test@");

    }

    @Test
    void insertBoard() {


        questionMapper.insertBoard(questionWriteDTO);   // 게시물 작성

//        List<QuestionListDTO> lists = questionMapper.selectListWithPage();
//        System.out.println("lists = " + lists);


    }

    @Test
    void selectBoard() {

    }

    @Test
    void selectTotal(){
        QuestionWriteDTO questionWriteDTO = new QuestionWriteDTO();
        questionWriteDTO.setMemberId(3L);
        questionWriteDTO.setQContent("test");
        questionWriteDTO.setQTitle("test");

        questionMapper.insertBoard(questionWriteDTO);
        int count = questionMapper.selectTotal(4L);
        System.out.println("@@@@@@@ count = " + count);
    }

    @Test
    void selectQuestion(){

        QuestionWriteDTO questionWriteDTO = new QuestionWriteDTO();
        questionWriteDTO.setMemberId(3L);
        questionWriteDTO.setQContent("test");
        questionWriteDTO.setQTitle("test");

        questionMapper.insertBoard(questionWriteDTO);

        Optional<QuestionDetailDTO> questionDetailDTO = questionMapper.selectQuestion(questionWriteDTO.getQBoardId());
        System.out.println("detail = " + questionDetailDTO.isPresent());

    }

    @Test
    void updateQuestion(){
        QuestionWriteDTO questionWriteDTO = new QuestionWriteDTO();
        questionWriteDTO.setMemberId(3L);
        questionWriteDTO.setQContent("test");
        questionWriteDTO.setQTitle("test");

        questionMapper.insertBoard(questionWriteDTO);

        QuestionModifyDTO questionModifyDTO = new QuestionModifyDTO();
        questionModifyDTO.setQBoardId(questionWriteDTO.getQBoardId());
        questionModifyDTO.setQContent("testerwerwer");
        questionModifyDTO.setQTitle("testsdfsfes");

        questionMapper.updateQuestion(questionModifyDTO);

        System.out.println(questionModifyDTO + "############");
    }



    @Test
    public void selectQuestionList(){
        QuestionSearchDTO questionSearchDTO = new QuestionSearchDTO();
        questionSearchDTO.setMemberId(4L);
        questionSearchDTO.setSearchType("qTitle");
        questionSearchDTO.setKeyword("test");

//        List<QuestionListDTO> searchListDTOS = questionMapper.searchList(questionSearchDTO);
//
//        System.out.println(searchListDTOS);
    }

    @Test
    public void selectListWithPage(){

        QPageRequestDTO qPageRequestDTO = new QPageRequestDTO();
        qPageRequestDTO.setMemberId(4L);
        qPageRequestDTO.setPage(1);
        qPageRequestDTO.setAmount(3);

        List<QuestionListDTO> listPages = questionMapper.selectListWithPage(qPageRequestDTO);
        System.out.println(listPages);


    }


    @Test
    public void searchListWithPage(){
        QPageRequestDTO qPageRequestDTO = new QPageRequestDTO();
        qPageRequestDTO.setMemberId(4L);
        qPageRequestDTO.setPage(1);
        qPageRequestDTO.setAmount(3);

        List<QuestionListDTO> list = questionMapper.searchListWithPage(qPageRequestDTO.getMemberId(),"s",1,3);
        System.out.println("list = " + list);

        questionMapper.searchListTotal(qPageRequestDTO.getMemberId(),"s");


    }

    @Test
    public void deleteQuestion(){
        questionMapper.insertBoard(questionWriteDTO);

        questionMapper.deleteQuestion(questionWriteDTO.getQBoardId());
        questionMapper.deleteAnswer(questionWriteDTO.getQBoardId());
    }




}