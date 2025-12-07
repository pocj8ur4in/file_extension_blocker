package kr.co.pocj8ur4in.blocker.extension.file.controller;

import jakarta.validation.Valid;
import kr.co.pocj8ur4in.blocker.extension.file.controller.dto.AddCustomExtensionRequest;
import kr.co.pocj8ur4in.blocker.extension.file.controller.dto.AddDefaultExtensionRequest;
import kr.co.pocj8ur4in.blocker.extension.file.exception.DuplicateExtensionException;
import kr.co.pocj8ur4in.blocker.extension.file.exception.EmptyExtensionException;
import kr.co.pocj8ur4in.blocker.extension.file.exception.ExistDefaultExtensionException;
import kr.co.pocj8ur4in.blocker.extension.file.exception.ExtensionNotFoundException;
import kr.co.pocj8ur4in.blocker.extension.file.exception.InvalidExtensionFormatException;
import kr.co.pocj8ur4in.blocker.extension.file.exception.MaxExtensionLimitException;
import kr.co.pocj8ur4in.blocker.extension.file.service.FileExtensionService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class FileExtensionController {
    private static final int MAX_CUSTOM_EXTENSIONS = 200;

    private final FileExtensionService fileExtensionService;

    public FileExtensionController(FileExtensionService fileExtensionService) {
        this.fileExtensionService = fileExtensionService;
    }

    @GetMapping()
    public String getFileExtensionBlockerPage(Model model) {
        var customExtensions = fileExtensionService.listCustomExtensions();

        model.addAttribute("defaultExtensions", fileExtensionService.listDefaultExtensions());
        model.addAttribute("customExtensions", customExtensions);
        model.addAttribute("customExtensionCount", customExtensions.size());
        model.addAttribute("maxCustomExtensions", MAX_CUSTOM_EXTENSIONS);
        return "index";
    }

    @PostMapping("/custom")
    public String addCustomExtension(
            @ModelAttribute @Valid AddCustomExtensionRequest request,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            String errorMessage =
                    bindingResult.getFieldErrors().stream()
                            .findFirst()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .orElse("입력값이 올바르지 않습니다.");
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/";
        }

        try {
            fileExtensionService.saveCustomExtension(request);
        } catch (DuplicateExtensionException
                | ExistDefaultExtensionException
                | EmptyExtensionException
                | InvalidExtensionFormatException
                | MaxExtensionLimitException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/custom/{extensionUuid}/delete")
    public String removeCustomExtension(
            @PathVariable String extensionUuid, RedirectAttributes redirectAttributes) {
        try {
            fileExtensionService.removeCustomExtension(extensionUuid);
        } catch (ExtensionNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/default")
    public String addDefaultExtension(
            @ModelAttribute @Valid AddDefaultExtensionRequest request,
            RedirectAttributes redirectAttributes) {
        try {
            fileExtensionService.saveDefaultExtension(request);
        } catch (ExtensionNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }
}
