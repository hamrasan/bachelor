package cz.fel.cvut.hamrasan.gardener.rest;

import java.io.InputStream;

import cz.fel.cvut.hamrasan.gardener.security.SecurityConstants;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/gallery")
@CrossOrigin(origins = SecurityConstants.ORIGIN_URI, allowCredentials="true")
public class GalleryController {

    @GetMapping(value = "/{src}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable String src) throws IOException {
        return IOUtils.toByteArray(getClass().getResourceAsStream("/static/images/" + src));
    }
}
