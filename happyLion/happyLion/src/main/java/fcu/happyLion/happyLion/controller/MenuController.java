package fcu.happyLion.happyLion.controller;

import fcu.happyLion.happyLion.model.Menu;
import fcu.happyLion.happyLion.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/menu")
public class MenuController {

  @Autowired
  MenuService menuService;

  @GetMapping("")
  public List<Menu> getAllMenus() {
    return menuService.getAllMenus();
  }

  @GetMapping("/{id}")
  public Menu getMenuById(@PathVariable int id) {
    return menuService.getMenuById(id);
  }

  // search menu by restaurant's ID
  @GetMapping("/rest-id/{restId}")
  public List<Menu> getMenuByRestId(@PathVariable int restId) {
    return menuService.getMenuByRestId(restId);
  }

  @GetMapping("/item/{keyword}")
  public List<Menu> searchMenusByItem(@PathVariable String keyword) {
    return menuService.searchMenusByItem(keyword);
  }

  @PostMapping("")
  public Menu createMenu(@RequestBody Menu menu) {
    return menuService.createMenu(menu);
  }

  @PutMapping("")
  public Menu updateMenu(@RequestBody Menu menu) {
    return menuService.updateMenu(menu);
  }

  @DeleteMapping("/{id}")
  public int deleteMenu(@PathVariable int id) {
    return menuService.deleteMenu(id);
  }

} // end of MenuController
