module.exports = function(grunt){
	'use strict'
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),

		uglify: {
			options:{
				mangle: false,
				beautify: false,
				stripBanners: true,
				banner: '/*<%=pkg.name%>-<%=pkg.version%>-lib@<%=grunt.template.today("yyyy-mm-dd")%>*/\n'
			},
			production: {
				files:{
					'dist/js/lib.all.js':[
						'bower_components/jquery/dist/jquery.min.js',
						'bower_components/bootstrap/dist/js/bootstrap.min.js',
						'bower_components/jquery-slimscroll/jquery.slimscroll.min.js',
						'bower_components/fastclick/lib/fastclick.js',
						'plugins/input-mask/jquery.inputmask.js',
						'plugins/input-mask/jquery.inputmask.date.extensions.js',
						'bower_components/datatables.net/js/jquery.dataTables.min.js',
						'bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js',
						'bower_components/moment/min/moment.min.js',
						'bower_components/bootstrap-daterangepicker/daterangepicker.js',
						'plugins/timepicker/bootstrap-timepicker.min.js',
						'plugins/iCheck/icheck.min.js',
						]
				}
			}
		},
		concat_css: {
			options:{
				beautify: false,
				stripBanners: true,
				banner: '/*<%=pkg.name%>-<%=pkg.version%>@<%=grunt.template.today("yyyy-mm-dd")%>*/\n'
			},
			css:{
				files:{
					'dist/css/lib.all.css': [
						'bower_components/bootstrap/dist/css/bootstrap.min.css',
						'bower_components/font-awesome/css/font-awesome.min.css',
						'bower_components/Ionicons/css/ionicons.min.css',
						'bower_components/bootstrap-daterangepicker/daterangepicker.css',
						'bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css',
						'plugins/iCheck/all.css',
						'plugins/timepicker/bootstrap-timepicker.min.css',
						'bower_components/select2/dist/css/select2.min.css',
						'bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css',
						'dist/css/AdminLTE.css',
						'dist/css/skins/_all-skins.min.css'
					]
				}
			}
		}
	});
	grunt.loadNpmTasks("grunt-contrib-uglify");
	grunt.loadNpmTasks("grunt-contrib-jshint");
	grunt.loadNpmTasks("grunt-contrib-concat");
	grunt.loadNpmTasks("grunt-concat-css");

	grunt.registerTask('default', []);

	grunt.registerTask('prod', ['uglify','concat_css']);
};