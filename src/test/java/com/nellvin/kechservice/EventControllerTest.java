package com.nellvin.kechservice;

import com.nellvin.kechservice.controler.EventController;
import com.nellvin.kechservice.model.Event;
import com.nellvin.kechservice.repository.EventRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
public class EventControllerTest {

//    @InjectMo
    EventController controller;

    @Mock
    EventRepository repository;

    @Test
    public void testFindAll()
    {
        // given
        Event event1 = new Event("church_service");
        Event event2 = new Event("bible_study");
        List<Event> eventList = Arrays.asList(event1, event2);


        // when
        when(repository.findAll()).thenReturn(eventList);
        List<Event> result = controller.getAllEvents();

        // then
        assertThat(result.get(0).getName()).isEqualTo(event1.getName());
        assertThat(result.get(1).getName()).isEqualTo(event2.getName());

    }

//        assertThat(result.getEmployeeList().size()).isEqualTo(2);

//        assertThat(result.getEmployeeList().get(0).getFirstName())
//                .isEqualTo(employee1.getFirstName());

//        assertThat(result.getEmployeeList().get(1).getFirstName())
//                .isEqualTo(employee2.getFirstName());

//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private EventService eventService;
//
//    @Test
//    public void test()
//            throws Exception {
//
//        Event event = new Event();
//        event.setName("church_service");
//
//        List<Event> allEvents = Arrays.asList(event);
//
//        given(eventService.retrieveEvents()).willReturn(allEvents);
//
//        mvc.perform(get("/api/employees")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name", is(alex.getName())));
//    }
}
