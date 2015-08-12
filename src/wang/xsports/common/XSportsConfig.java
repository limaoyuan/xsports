package wang.xsports.common;


import org.beetl.ext.jfinal.BeetlRenderFactory;
import org.beetl.ext.jsp.IncludeJSPTag;

import wang.xsports.home.controller.HomeController;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.JspRender;

/**
 * API引导式配置
 */
public class XSportsConfig extends JFinalConfig {
	
	/**
	 * 配置常量
	 */
	@Override
    public void configConstant(Constants me) {
        loadPropertyFile("config.txt"); // 加载少量必要配置，随后可用getProperty(...)获取值
		me.setDevMode(getPropertyToBoolean("devMode", false));
		//me.setViewType(ViewType.JSP); 							// 设置视图类型为Jsp，否则默认为FreeMarker
        // beetl模版
        BeetlRenderFactory beetlRenderFactory = new BeetlRenderFactory();
        BeetlRenderFactory.groupTemplate.registerTag("includeJSP", IncludeJSPTag.class);
        me.setMainRenderFactory(beetlRenderFactory);
	}
	
	/**
	 * 配置路由
	 */
	@Override
    public void configRoute(Routes me) {
        // 不需要将对象转为map
        JspRender.setSupportActiveRecord(false);
        me.add("/", HomeController.class, "/index");
        // me.add("/sprintplan", SprintPlanController.class, "/sprintplan");
        // me.add("/sprintbacklog", SprintBacklogController.class,
        // "/sprintbacklog"); // 第三个参数为该Controller的视图存放路径
        // me.add("/productbacklog", ProductBacklogController.class,
        // "/productbacklog"); // 第三个参数为该Controller的视图存放路径
        // me.add("/blog", BlogController.class); // 第三个参数省略时默认与第一个参数值相同，在此即为
        // "/blog"
	}
	
	/**
	 * 配置插件
	 */
	@Override
    public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
		me.add(c3p0Plugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
//        AutoTableBindPlugin autoTableBind = new AutoTableBindPlugin(c3p0Plugin.getDataSource(), arp);
//        me.add(autoTableBind);

        // arp.addMapping("sprint_backlog", "sprint_backlog_id",
        // SprintBacklog.class);
        // arp.addMapping("product_backlog", "product_backlog_id",
        // ProductBacklog.class);
        // arp.addMapping("sprint_plan", "sprint_plan_id", SprintPlan.class);
        // arp.addMapping("user", "user_id", User.class);
	}
	
	/**
	 * 配置全局拦截器
	 */
	@Override
    public void configInterceptor(Interceptors me) {
		
	}
	
	/**
	 * 配置处理器
	 */
	@Override
    public void configHandler(Handlers me) {
		
	}
	
	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
        JFinal.start("WebRoot", 5555, "/", 5);
	}
}
